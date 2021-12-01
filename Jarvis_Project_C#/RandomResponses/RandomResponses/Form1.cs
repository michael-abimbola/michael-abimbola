using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Speech.Synthesis;
using System.Speech.Recognition;
using System.Globalization;
using System.Diagnostics;

namespace RandomResponses
{
    public partial class Form1 : Form
    {
        SpeechSynthesizer s = new SpeechSynthesizer();
        bool wake = true;
        Choices choices = new Choices();
        string name;

        public Form1()
        {
            InitializeComponent();
            SpeechRecognitionEngine sre = new SpeechRecognitionEngine();
            choices.Add(new string[] { "hello jarvis", "how are you doing jarvis",
                "jarvis what is todays date", "jarvis what is the time", 
                "jarvis open google chrome", "jarvis open whatsapp", "jarvis open sublime",
                "jarvis", "jarvis sleep mode", "jarvis shutdown", "jarvis restart system"});
            Grammar grm = new Grammar(new GrammarBuilder(choices));
            try
            {
                sre.RequestRecognizerUpdate();
                sre.LoadGrammar(grm);
                sre.SpeechRecognized += Sre_SpeechRecognized;
                sre.SetInputToDefaultAudioDevice();
                sre.RecognizeAsync(RecognizeMode.Multiple);
            }
            catch
            {

            }
            s.SelectVoiceByHints(VoiceGender.Male, VoiceAge.Adult, 0, CultureInfo.GetCultureInfo("en-gb"));

        }

        public void jarvis(string jarvisReply)
        {
            s.Speak(jarvisReply);
        }

        public void restart()
        {
            //Process.Start(@"M:\Personal projects\RandomResponses\RandomResponses\bin\Debug\RandomResponses.exe");
            jarvis($"System restarting");
        }
        private void Sre_SpeechRecognized(object sender, SpeechRecognizedEventArgs e)
        {
            string command = e.Result.Text;
            if (name != "" && name == "Michael")
            {
                if (command == "jarvis")
                {
                    wake = true;
                    jarvis($"Hello sir");
                }
                else
                {
                    if (command == "jarvis sleep mode")
                    {
                        wake = false;
                        jarvis($"See you later {name}" );
                    }
                }

                if (wake == true)
                {
                    if(command == "jarvis restart system")
                    {
                        this.Hide();
                        restart();                       
                    }
                    if (command == "hello jarvis")
                    {
                        jarvis($"Welcome back Sir");
                    }
                    else
                    {
                        if (command == "how are you doing jarvis")
                        {
                            jarvis($"I am doing great Sir, thanks for asking");
                        }
                        else
                        {
                            if (command == "jarvis what is todays date")
                            {
                                jarvis($"Today's date is " + DateTime.Now.ToString("M/d/yyy"));
                            }
                            else
                            {
                                if (command == "jarvis what is the time")
                                {
                                    jarvis($"The current time is " + DateTime.Now.ToString("h:mm tt"));
                                }
                                else
                                {
                                    if (command == "jarvis open google chrome")
                                    {
                                        Process.Start("https://www.google.com/");
                                    }
                                    else
                                    {
                                        if (command == "jarvis open whatsapp")
                                        {
                                            Process.Start("https://web.whatsapp.com/");
                                        }
                                        else
                                        {
                                            //doesnt work for now
                                            if (command == "jarvis open sublime")
                                            {
                                                Process.Start(@"C:\Program Files\Sublime Text 3\sublime_text");
                                            }
                                            else
                                            {
                                                if (command == "jarvis shutdown")
                                                {
                                                    jarvis($"Good day sir");
                                                    this.Hide();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                jarvis($"would you mind inserting your name sir");
            }

        }

        private void button1_Click(object sender, EventArgs e)
        {
            string name = textBox2.Text;
        }
    }
}
