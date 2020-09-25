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
                    return "Проблема с подключением (возможно нет интернета или сервер недоступен)";
                }

                Listener.SetSocket(socket);

                string handshakeResponse = readResponseTimeout(5000);

                if (handshakeResponse.Equals(""))
                {
                    Listener.ResetSocket();
                    return "Сервер так и не прислал ответ";
                }

                if (!Parser.FieldDeserialize<string>(handshakeResponse, "operation").Equals("handshake"))
                {
                    Listener.ResetSocket();
                    return $"Сервер прислал не тот ответ, что ожидался: {Parser.FieldDeserialize<int>(handshakeResponse, "operation")}";
                }

                string payload = Parser.FieldDeserialize<string>(handshakeResponse, "payload");
                int codeResponse = Parser.FieldDeserialize<int>(payload, "code");

                if (codeResponse >= 40)
                {
                    Listener.ResetSocket();
                    switch (codeResponse)
                    {
                        case 40: return "Сервер не принял операцию handshake";
                        case 41: return "Сервер не принял соединение по причине его не распознавания (проверь payload)";
                        case 42: return "Такое имя уже зарегистрировано в системе, попробуйте другое";
                        default: return "Сервер не принял запрос, возможно, нет свободных мест";
                    }
                }
                if (codeResponse.Equals(30))
                {
                    Listener.ResetSocket();
                    port = Parser.FieldDeserialize<int>(payload, "port");
                }
                else if(codeResponse.Equals(20))
                {
                    readyToExit = true;
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

                socket.Send(msg);

                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public static void Disconnect()
        {
            byte[] msg = Encoding.UTF8.GetBytes(prepareRequest(Parser.RequestSerialize("close")));

            socket.Send(msg);

            Close();
        }

        public static void Close()
        {
            socket.Shutdown(SocketShutdown.Both);
            socket.Close();
            socket = null;
        }

        private static string readResponseTimeout(int millisecondsTimeout)
        {
            double start = (DateTime.Now - new DateTime(1970, 1, 1)).TotalMilliseconds;

            do
            {
                if (Listener.HasNewMessageToRead)
                {
                    return Listener.ReadTheOldestMessage();
                }

                Thread.Sleep(50);
            } while (((DateTime.Now - new DateTime(1970, 1, 1)).TotalMilliseconds - start) < millisecondsTimeout);

            return "";
        }

        private static string prepareRequest(string request)
        {
            return string.Concat(request, '\n');
        }
    }
}
