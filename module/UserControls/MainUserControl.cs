using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace module.UserControls
{
    public partial class MainUserControl : UserControl
    {
        public MainUserControl()
        {
            InitializeComponent();
        }

        private void timerCheckListener_Tick(object sender, EventArgs e)
        {
            if (Listener.HasNewMessageToRead)
            {
                string message = Listener.ReadTheOldestMessage();
                string operation = Parser.FieldDeserialize<string>(message, "operation");

                switch (operation)
                {
                    case "notify": throw new NotImplementedException();
                    case "close": close(); break;
                    default:
                        timerCheckListener.Enabled = false; 
                        MessageBox.Show($"Сервер прислал не поддерживаемую операцию - {operation}");
                        timerCheckListener.Enabled = true;
                        break;
                }
            }
        }

        private void close()
        {
            timerCheckListener.Enabled = false;
            Listener.ResetSocket();
            Request.Close();
            MessageBox.Show("Сервер закрыл теукщее подключение");
            MainForm.ResetModuleName();
            MainForm.RemoveTopUserControl();
        }

        private void pictureBoxIconExit_Click(object sender, EventArgs e)
        {
            timerCheckListener.Enabled = false;

            if (MessageBox.Show("Вы хотите разорвать текущее подключение с сервером?", "Внимание", MessageBoxButtons.OKCancel).Equals(DialogResult.OK))
            {
                Listener.ResetSocket();
                Request.Disconnect();
                MainForm.ResetModuleName();
                MainForm.RemoveTopUserControl();
            }
            else
            {
                timerCheckListener.Enabled = true;
            }
        }

        private void pictureBoxIconNotification_Click(object sender, EventArgs e)
        {
            // TODO
        }

        private void labelTabMessage_Click(object sender, EventArgs e)
        {
            // TODO
        }

        private void labelTabSubscribe_Click(object sender, EventArgs e)
        {
            // TODO
        }
    }
}
