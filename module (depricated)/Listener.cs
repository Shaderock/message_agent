using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace module
{
    public class Listener
    {
        public static object Work { get; } = new object();
        public static bool HasNewMessageToRead { get; private set; } = false;

        private static Socket socket = null;
        private static bool needListen = false;
        private static List<string> messageList = new List<string>();
        private static bool isPaused = false;

        public static async void SetSocket(Socket socket)
        {
            bool wasListening = needListen;

            lock (Work)
            {
                needListen = socket != null;
                Listener.socket = socket;
            }

            if (!wasListening && needListen)
            {
                await Task.Run(() => listen());
            }
        }

        public static void ResetSocket()
        {
            SetSocket(null);
        }

        private static void listen()
        {
            while (true)
            {
                lock(Work)
                {
                    if (!needListen)
                    {
                        return;
                    }

                    if (socket.Poll(50000, SelectMode.SelectRead))
                    {
                        int available = socket.Available;
                        if (available.Equals(0))
                        {
                            return;
                        }
                        else
                        {
                            lock (messageList)
                            {
                                byte[] newMessage = new byte[available];
                                socket.Receive(newMessage, available, SocketFlags.None);
                                messageList.Add(Encoding.UTF8.GetString(newMessage));
                                HasNewMessageToRead = true;
                            }
                        }
                    }
                }
            }
        }

        public async static void Pause()
        {
            await Task.Run(() => pause());
        }

        private static void pause()
        {
            if (!isPaused && needListen)
            {
                isPaused = true;
                lock (Work)
                {
                    while (isPaused) 
                    {
                        Thread.Sleep(50); 
                    }
                }
            }
        }

        public static void Resume()
        {
            isPaused = false;
        }

        public static string ReadTheOldestMessage()
        {
            lock (messageList)
            {
                if (messageList.Count != 0)
                {
                    string message = messageList[0];
                    messageList.RemoveAt(0);
                    HasNewMessageToRead = messageList.Count != 0;
                    return message;
                }
                else
                {
                    return null;
                }
            }
        }
    }
}
