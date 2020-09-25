using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Text.RegularExpressions;

namespace module.UserControls
{
    public partial class ConnectUserControl : UserControl
    {
        private bool labelSwitchState = false;
        private Regex ipRegex = new Regex(@"(^\d{1,3}(\.\d{1,3}){3}$)|(localhost)");
        private Regex portRegex = new Regex(@"^\d+$");
        public ConnectUserControl()
        {
            InitializeComponent();
        }

        private async void buttonConnect_Click(object sender, EventArgs e)
        {
            string result = null;
            buttonConnect.Enabled = false;
            await Task.Run(() => result = Request.Connect(textBoxIP.Text, int.Parse(textBoxPort.Text),
                textBoxModuleName.Text));
            
            if (result == null)
            {
                MainForm.SetNewModuleName(textBoxModuleName.Text);
                MainForm.AddUserControl(new MainUserControl());
            }
            else
            {
                MessageBox.Show(result);
            }

            buttonConnect.Enabled = true;
        }

        private void labelSwitch_Click(object sender, EventArgs e)
        {
            labelSwitchState = !labelSwitchState;

            labelSwitch.Text = $"Открыть доп. настройки {(labelSwitchState ? '▼' : '▶')}";

            labelIP.Visible = labelSwitchState;
            labelPort.Visible = labelSwitchState;
            textBoxIP.Visible = labelSwitchState;
            textBoxPort.Visible = labelSwitchState;
        }

        private void validateData()
        {
            buttonConnect.Enabled = validateName() && validateIP() && validatePort();
        }

        private bool validateName()
        {
            return !textBoxModuleName.Text.Equals("");
            
        }

        private bool validateIP()
        {
            if (ipRegex.IsMatch(textBoxIP.Text))
            {
                if (!textBoxIP.Text.Equals("localhost"))
                {
                    List<string> nums = textBoxIP.Text.Split('.').ToList();
                    foreach (string num in nums)
                    {
                        int n = int.Parse(num);
                        if (n > 255)
                        {
                            return false;
                        }
                    }
                }

                return true;
            }
            else
            {
                return false;
            }
        }

        private bool validatePort()
        {
            if (portRegex.IsMatch(textBoxPort.Text))
            {
                int port = int.Parse(textBoxPort.Text);
                return (port < 65535) && (port > 0);
            }
            else
            {
                return false;
            }
        }

        private void textBoxModule_TextChanged(object sender, EventArgs e)
        {
            validateData();
        }

        private void textBoxIP_TextChanged(object sender, EventArgs e)
        {
            if (textBoxIP.Text.Equals(""))
            {
                textBoxIP.Text = "89.28.116.199";
            }
            validateData();
        }

        private void textBoxPort_TextChanged(object sender, EventArgs e)
        {
            if (textBoxPort.Text.Equals(""))
            {
                textBoxPort.Text = "17001";
            }
            validateData();
        }
    }
}
