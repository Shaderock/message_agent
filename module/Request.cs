using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
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
                socket.Connect(ip, port);

                byte[] msg = Encoding.UTF8.GetBytes(Parser.RequestSerialize("handshake", new { name }));

                socket.Send(msg);

                socket.Receive(msg);

                string msgString = Encoding.UTF8.GetString(msg);

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
    }
}
