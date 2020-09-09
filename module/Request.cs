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
        
        public static bool Connect(string ip, int port, string name)
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

                byte[] msg = Encoding.UTF8.GetBytes(string.Concat(Parser.RequestSerialize("handshake", new { name }),
                    "\n"));

                int a = socket.Send(msg);
                //socket.Shutdown(SocketShutdown.Send);

                //Action<object, Socket, byte[]> method = socketRecieve;
                //object monitorSync = new object();
                //bool timedOut;
                //lock (monitorSync)
                //{
                //    msg = new byte[1024];
                //    method.BeginInvoke(monitorSync, socket, msg, null, null);
                //    timedOut = !Monitor.Wait(monitorSync, TimeSpan.FromSeconds(5));
                //}
                //if (timedOut)
                //{
                //    method.EndInvoke(null);
                //    throw new Exception("Response not recieved");
                //}

                var msgin = new byte[1024];
                socket.Receive(msgin);

                string msgString = Encoding.UTF8.GetString(msgin);

                socket.Shutdown(SocketShutdown.Both);
                socket.Close();
            }
            catch (Exception e)
            {
                socket = null;
                return false;
            }
                

            return true;
        }

        private static void socketRecieve(object monitorSync, Socket socket, byte[] msg)
        {
            socket.Receive(msg);

            lock(monitorSync)
            {
                Monitor.Pulse(monitorSync);
            }
        }
    }
}
