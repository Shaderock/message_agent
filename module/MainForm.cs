using module.UserControls;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace module
{
    public partial class MainForm : Form
    {
        private static readonly MainForm mainForm = new MainForm();

        private readonly List<UserControl> stackUserControls = new List<UserControl>();

        private MainForm()
        {
            InitializeComponent();
        }

        private UserControl getTopStack()
        {
            if (stackUserControls.Count != 0)
            {
                return stackUserControls[stackUserControls.Count - 1];
            }

            return null;
        }

        public static MainForm GetMainForm()
        {
            return mainForm;
        }

        public static void AddUserControl(UserControl newUserControl)
        {
            UserControl topUserControl = mainForm.getTopStack();
            if (topUserControl != null)
            {
                topUserControl.Visible = false;
            }

            mainForm.stackUserControls.Add(newUserControl);
            mainForm.Controls.Add(newUserControl);

            newUserControl.Visible = true;
            newUserControl.Location = new Point(0, 0);
            FixTopUserControlSize();
        }

        public static void RemoveTopUserControl()
        {
            if (mainForm.getTopStack() != null)
            {
                UserControl topUserControl = mainForm.getTopStack();
                topUserControl.Visible = false;

                mainForm.stackUserControls.Remove(topUserControl);
                mainForm.Controls.Remove(topUserControl);

                if (mainForm.getTopStack() != null)
                {
                    mainForm.getTopStack().Visible = true;
                    FixTopUserControlSize();
                }
            }
        }

        public static void ChangeTopUserControl(UserControl newUserControl)
        {
            RemoveTopUserControl();
            AddUserControl(newUserControl);
        }

        private static void FixTopUserControlSize()
        {
            UserControl topUserControl = mainForm.getTopStack();
            if (topUserControl != null)
            {
                topUserControl.Size = mainForm.ClientSize;
            }
        }

        private void MainForm_SizeChanged(object sender, EventArgs e)
        {
            FixTopUserControlSize();
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            // TODO when closing connection will be ready
        }

        public void SetNewModuleName(string newName)
        {
            Text = string.Concat($"Module{((newName == null || newName == "") ? "" : " - ")}", newName);
        }
    }
}
