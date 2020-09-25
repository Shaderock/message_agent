namespace module.UserControls
{
    partial class MainUserControl
    {
        /// <summary> 
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.tableLayoutPanelMain = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanelIcons = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanelIconExit = new System.Windows.Forms.TableLayoutPanel();
            this.pictureBoxIconExit = new System.Windows.Forms.PictureBox();
            this.tableLayoutPanelIconNotification = new System.Windows.Forms.TableLayoutPanel();
            this.pictureBoxIconNotification = new System.Windows.Forms.PictureBox();
            this.tableLayoutPanelHeader = new System.Windows.Forms.TableLayoutPanel();
            this.labelHeader = new System.Windows.Forms.Label();
            this.tableLayoutPanelNavigation = new System.Windows.Forms.TableLayoutPanel();
            this.labelTabMessage = new System.Windows.Forms.Label();
            this.labelTabSubscribe = new System.Windows.Forms.Label();
            this.tableLayoutPanelContent = new System.Windows.Forms.TableLayoutPanel();
            this.timerCheckListener = new System.Windows.Forms.Timer(this.components);
            this.tableLayoutPanelMain.SuspendLayout();
            this.tableLayoutPanelIcons.SuspendLayout();
            this.tableLayoutPanelIconExit.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxIconExit)).BeginInit();
            this.tableLayoutPanelIconNotification.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxIconNotification)).BeginInit();
            this.tableLayoutPanelHeader.SuspendLayout();
            this.tableLayoutPanelNavigation.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanelMain
            // 
            this.tableLayoutPanelMain.ColumnCount = 1;
            this.tableLayoutPanelMain.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelMain.Controls.Add(this.tableLayoutPanelIcons, 0, 0);
            this.tableLayoutPanelMain.Controls.Add(this.tableLayoutPanelNavigation, 0, 2);
            this.tableLayoutPanelMain.Controls.Add(this.tableLayoutPanelContent, 0, 1);
            this.tableLayoutPanelMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelMain.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanelMain.Name = "tableLayoutPanelMain";
            this.tableLayoutPanelMain.RowCount = 3;
            this.tableLayoutPanelMain.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 13.16964F));
            this.tableLayoutPanelMain.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 72.99107F));
            this.tableLayoutPanelMain.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 13.61607F));
            this.tableLayoutPanelMain.Size = new System.Drawing.Size(702, 491);
            this.tableLayoutPanelMain.TabIndex = 0;
            // 
            // tableLayoutPanelIcons
            // 
            this.tableLayoutPanelIcons.ColumnCount = 3;
            this.tableLayoutPanelIcons.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanelIcons.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 80F));
            this.tableLayoutPanelIcons.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanelIcons.Controls.Add(this.tableLayoutPanelIconExit, 0, 0);
            this.tableLayoutPanelIcons.Controls.Add(this.tableLayoutPanelIconNotification, 2, 0);
            this.tableLayoutPanelIcons.Controls.Add(this.tableLayoutPanelHeader, 1, 0);
            this.tableLayoutPanelIcons.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelIcons.Location = new System.Drawing.Point(3, 3);
            this.tableLayoutPanelIcons.Name = "tableLayoutPanelIcons";
            this.tableLayoutPanelIcons.RowCount = 1;
            this.tableLayoutPanelIcons.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelIcons.Size = new System.Drawing.Size(696, 58);
            this.tableLayoutPanelIcons.TabIndex = 0;
            // 
            // tableLayoutPanelIconExit
            // 
            this.tableLayoutPanelIconExit.ColumnCount = 2;
            this.tableLayoutPanelIconExit.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33F));
            this.tableLayoutPanelIconExit.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 67F));
            this.tableLayoutPanelIconExit.Controls.Add(this.pictureBoxIconExit, 1, 1);
            this.tableLayoutPanelIconExit.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelIconExit.Location = new System.Drawing.Point(3, 3);
            this.tableLayoutPanelIconExit.Name = "tableLayoutPanelIconExit";
            this.tableLayoutPanelIconExit.RowCount = 2;
            this.tableLayoutPanelIconExit.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
            this.tableLayoutPanelIconExit.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 67F));
            this.tableLayoutPanelIconExit.Size = new System.Drawing.Size(63, 52);
            this.tableLayoutPanelIconExit.TabIndex = 0;
            // 
            // pictureBoxIconExit
            // 
            this.pictureBoxIconExit.Cursor = System.Windows.Forms.Cursors.Hand;
            this.pictureBoxIconExit.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBoxIconExit.Image = global::module.Properties.Resources.exit_icon;
            this.pictureBoxIconExit.Location = new System.Drawing.Point(23, 20);
            this.pictureBoxIconExit.Name = "pictureBoxIconExit";
            this.pictureBoxIconExit.Size = new System.Drawing.Size(37, 29);
            this.pictureBoxIconExit.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBoxIconExit.TabIndex = 0;
            this.pictureBoxIconExit.TabStop = false;
            this.pictureBoxIconExit.Click += new System.EventHandler(this.pictureBoxIconExit_Click);
            // 
            // tableLayoutPanelIconNotification
            // 
            this.tableLayoutPanelIconNotification.ColumnCount = 2;
            this.tableLayoutPanelIconNotification.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 67F));
            this.tableLayoutPanelIconNotification.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33F));
            this.tableLayoutPanelIconNotification.Controls.Add(this.pictureBoxIconNotification, 0, 1);
            this.tableLayoutPanelIconNotification.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelIconNotification.Location = new System.Drawing.Point(628, 3);
            this.tableLayoutPanelIconNotification.Name = "tableLayoutPanelIconNotification";
            this.tableLayoutPanelIconNotification.RowCount = 2;
            this.tableLayoutPanelIconNotification.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
            this.tableLayoutPanelIconNotification.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 67F));
            this.tableLayoutPanelIconNotification.Size = new System.Drawing.Size(65, 52);
            this.tableLayoutPanelIconNotification.TabIndex = 1;
            // 
            // pictureBoxIconNotification
            // 
            this.pictureBoxIconNotification.Cursor = System.Windows.Forms.Cursors.Hand;
            this.pictureBoxIconNotification.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBoxIconNotification.Image = global::module.Properties.Resources.bell_icon;
            this.pictureBoxIconNotification.Location = new System.Drawing.Point(3, 20);
            this.pictureBoxIconNotification.Name = "pictureBoxIconNotification";
            this.pictureBoxIconNotification.Size = new System.Drawing.Size(37, 29);
            this.pictureBoxIconNotification.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBoxIconNotification.TabIndex = 1;
            this.pictureBoxIconNotification.TabStop = false;
            this.pictureBoxIconNotification.Click += new System.EventHandler(this.pictureBoxIconNotification_Click);
            // 
            // tableLayoutPanelHeader
            // 
            this.tableLayoutPanelHeader.ColumnCount = 1;
            this.tableLayoutPanelHeader.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelHeader.Controls.Add(this.labelHeader, 0, 1);
            this.tableLayoutPanelHeader.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelHeader.Location = new System.Drawing.Point(72, 3);
            this.tableLayoutPanelHeader.Name = "tableLayoutPanelHeader";
            this.tableLayoutPanelHeader.RowCount = 2;
            this.tableLayoutPanelHeader.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
            this.tableLayoutPanelHeader.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 67F));
            this.tableLayoutPanelHeader.Size = new System.Drawing.Size(550, 52);
            this.tableLayoutPanelHeader.TabIndex = 2;
            // 
            // labelHeader
            // 
            this.labelHeader.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelHeader.AutoSize = true;
            this.labelHeader.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelHeader.Location = new System.Drawing.Point(165, 22);
            this.labelHeader.Name = "labelHeader";
            this.labelHeader.Size = new System.Drawing.Size(219, 24);
            this.labelHeader.TabIndex = 0;
            this.labelHeader.Text = "Отправка сообщения";
            // 
            // tableLayoutPanelNavigation
            // 
            this.tableLayoutPanelNavigation.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.Single;
            this.tableLayoutPanelNavigation.ColumnCount = 2;
            this.tableLayoutPanelNavigation.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelNavigation.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelNavigation.Controls.Add(this.labelTabMessage, 0, 0);
            this.tableLayoutPanelNavigation.Controls.Add(this.labelTabSubscribe, 1, 0);
            this.tableLayoutPanelNavigation.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelNavigation.Location = new System.Drawing.Point(3, 426);
            this.tableLayoutPanelNavigation.Name = "tableLayoutPanelNavigation";
            this.tableLayoutPanelNavigation.RowCount = 1;
            this.tableLayoutPanelNavigation.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelNavigation.Size = new System.Drawing.Size(696, 62);
            this.tableLayoutPanelNavigation.TabIndex = 1;
            // 
            // labelTabMessage
            // 
            this.labelTabMessage.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelTabMessage.AutoSize = true;
            this.labelTabMessage.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Underline, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelTabMessage.Location = new System.Drawing.Point(97, 23);
            this.labelTabMessage.Name = "labelTabMessage";
            this.labelTabMessage.Size = new System.Drawing.Size(154, 16);
            this.labelTabMessage.TabIndex = 0;
            this.labelTabMessage.Text = "Отправить сообщение";
            this.labelTabMessage.Click += new System.EventHandler(this.labelTabMessage_Click);
            // 
            // labelTabSubscribe
            // 
            this.labelTabSubscribe.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.labelTabSubscribe.AutoSize = true;
            this.labelTabSubscribe.Cursor = System.Windows.Forms.Cursors.Hand;
            this.labelTabSubscribe.Location = new System.Drawing.Point(465, 24);
            this.labelTabSubscribe.Name = "labelTabSubscribe";
            this.labelTabSubscribe.Size = new System.Drawing.Size(112, 13);
            this.labelTabSubscribe.TabIndex = 1;
            this.labelTabSubscribe.Text = "Настроить подписки";
            this.labelTabSubscribe.Click += new System.EventHandler(this.labelTabSubscribe_Click);
            // 
            // tableLayoutPanelContent
            // 
            this.tableLayoutPanelContent.ColumnCount = 3;
            this.tableLayoutPanelContent.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 3.5F));
            this.tableLayoutPanelContent.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 93F));
            this.tableLayoutPanelContent.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 3.5F));
            this.tableLayoutPanelContent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelContent.Location = new System.Drawing.Point(3, 67);
            this.tableLayoutPanelContent.Name = "tableLayoutPanelContent";
            this.tableLayoutPanelContent.RowCount = 3;
            this.tableLayoutPanelContent.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 9F));
            this.tableLayoutPanelContent.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 82F));
            this.tableLayoutPanelContent.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 9F));
            this.tableLayoutPanelContent.Size = new System.Drawing.Size(696, 353);
            this.tableLayoutPanelContent.TabIndex = 2;
            // 
            // timerCheckListener
            // 
            this.timerCheckListener.Enabled = true;
            this.timerCheckListener.Interval = 1000;
            this.timerCheckListener.Tick += new System.EventHandler(this.timerCheckListener_Tick);
            // 
            // MainUserControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanelMain);
            this.Name = "MainUserControl";
            this.Size = new System.Drawing.Size(702, 491);
            this.tableLayoutPanelMain.ResumeLayout(false);
            this.tableLayoutPanelIcons.ResumeLayout(false);
            this.tableLayoutPanelIconExit.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxIconExit)).EndInit();
            this.tableLayoutPanelIconNotification.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxIconNotification)).EndInit();
            this.tableLayoutPanelHeader.ResumeLayout(false);
            this.tableLayoutPanelHeader.PerformLayout();
            this.tableLayoutPanelNavigation.ResumeLayout(false);
            this.tableLayoutPanelNavigation.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelMain;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelIcons;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelIconExit;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelIconNotification;
        private System.Windows.Forms.PictureBox pictureBoxIconExit;
        private System.Windows.Forms.PictureBox pictureBoxIconNotification;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelHeader;
        private System.Windows.Forms.Label labelHeader;
        private System.Windows.Forms.Timer timerCheckListener;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelNavigation;
        private System.Windows.Forms.Label labelTabMessage;
        private System.Windows.Forms.Label labelTabSubscribe;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelContent;
    }
}
