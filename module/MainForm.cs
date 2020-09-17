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
        public static MainForm instance { get; } = new MainForm();

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

        public static void AddUserControl(UserControl newUserControl)
        {
            UserControl topUserControl = instance.getTopStack();
            if (topUserControl != null)
            {
                topUserControl.Visible = false;
            }

            instance.stackUserControls.Add(newUserControl);
            instance.Controls.Add(newUserControl);

            newUserControl.Visible = true;
            newUserControl.Location = new Point(0, 0);
            FixTopUserControlSize();
        }

        public static void RemoveTopUserControl()
        {
            if (instance.getTopStack() != null)
            {
                UserControl topUserControl = instance.getTopStack();
                topUserControl.Visible = false;

                instance.stackUserControls.Remove(topUserControl);
                instance.Controls.Remove(topUserControl);

                if (instance.getTopStack() != null)
                {
                    instance.getTopStack().Visible = true;
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
            UserControl topUserControl = instance.getTopStack();
            if (topUserControl != null)
            {
                topUserControl.Size = instance.ClientSize;
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

        public static void SetNewModuleName(string newName)
        {
            instance.Text = string.Concat($"Module{((newName == null || newName == "") ? "" : " - ")}", newName);
        }

        public static void ResetModuleName()
        {
            SetNewModuleName(null);
        }

        public static void ShowLoad()
        {
            // TODO
        }

        public static void HideLoad()
        {            
            // TODO
        }
    }
}
