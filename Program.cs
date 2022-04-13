using System;
using System.Text;

namespace ConsoleApp2
{
    public class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            Console.WriteLine(BreakText("In 1991, while studying computer science at University of Helsinki, Linus Torvalds began a project that later became the Linux kernel. He wrote the program specifically for the hardware he was using and independent of an operating system because he wanted to use the functions of his new PC with an 80386 processor. Development was done on MINIX using the GNU C Compiler.", 40));
        }

        private static string BreakText(string fullText, int count)
        {
            StringBuilder sentence = new StringBuilder();
            var arrayText = fullText.Split(' ');

            var counter = 0;
            for (int index = 0; index < arrayText.Length; index++)
            {
                counter += arrayText[index].Length + 1;

                if (counter >= count)
                {
                    sentence.Append($"{arrayText[index]} \n");
                    counter = 0;
                }
                else 
                { 
                    sentence.Append($"{arrayText[index]} "); 
                }
                
            }
            return sentence.ToString();
        }
    }
}
