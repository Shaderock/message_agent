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
    public partial class ConnectUserControl : UserControl
    {
        private bool labelSwitchState = false;
        public ConnectUserControl()
        {
            InitializeComponent();
        }

        private void buttonConnect_Click(object sender, EventArgs e)
        {

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
    }
}
