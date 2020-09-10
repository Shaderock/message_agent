using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace module
{
    public class Request
    {
        private static Socket socket;
        
        public static string Connect(string ip, int port, string name)
        {
            bool readyToExit = false;

            while (!readyToExit)
            {
                bool result = ConnectSend(ip, port, name);
                if (!result)
                {
                    return "Проблема с подключением";
                }

                Listener.SetSocket(socket);

                string handshakeResponse = readResponseTimeout(5000);

                if (handshakeResponse.Equals(""))
                {
                    return "Сервер так и не прислал ответ";
                }


            }

            

            return null;
        }

        private static bool ConnectSend(string ip, int port, string name)
        {
            try
            {
                if (ip.Equals("localhost"))
                    ip = "127.0.0.1";

                //IPHostEntry host = Dns.GetHostEntry(ip);
                //IPAddress ipAddress = host.AddressList[0];
                //IPEndPoint remoteEP = new IPEndPoint(ipAddress, port);

                //socket = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
                //socket.Connect(remoteEP);

                socket = new Socket(SocketType.Stream, ProtocolType.Tcp);
                //socket.NoDelay = true;
                socket.Connect(ip, port);

                byte[] msg = Encoding.UTF8.GetBytes(prepareRequest(Parser.RequestSerialize("handshake", new { name })));

                int a = socket.Send(msg);

                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        private static string readResponseTimeout(int millisecondsTimeout)
        {
            int start = DateTime.Now.Millisecond;

            do
            {
                if (Listener.HasNewMessageToRead)
                {
                    return Listener.ReadTheOldestMessage();
                }

                Thread.Sleep(50);
            } while ((DateTime.Now.Millisecond - start) < millisecondsTimeout);

            return null;
        }

        private static string prepareRequest(string request)
        {
            return string.Concat(request, '\n');
        }
    }
}
