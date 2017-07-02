using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace ToolCreateQuery
{
    class Program
    {
        // For Android
        public static string ConvertToUnsign(string str)
        {
            //Convert unicode to ascii character
            //Because Android Drawable Resource can't contain unicode
            Regex regex = new Regex("\\p{IsCombiningDiacriticalMarks}+");
            string temp = str.Normalize(System.Text.NormalizationForm.FormD);
            return regex.Replace(temp, String.Empty)
                .Replace('\u0111', 'd').Replace('\u0110', 'D');

        }

        //Function to get all file name in folder
        private static List<string> getListCountry()
        {
            DirectoryInfo d = new DirectoryInfo(@"E:\14_Joshua_Git\Android-Tutorial-from-Scratch\Ch031_FlagsQuizApp\flags_style4_large");
            FileInfo[] files = d.GetFiles("*.png");
            List<string> lstNames = new List<string>();
            foreach (var file in files)
            {
                //Add this line to rename all Upper file name to Lower file name
                //Android Drawable Resource canm't contain "'" and "-" character
                //So I removed it
                //OK ! Just run and get query
                File.Move(file.FullName, ConvertToUnsign(file.Name.ToLower().Replace("'", String.Empty).Replace("-", String.Empty)));
                lstNames.Add(file.Name.Replace(".png", String.Empty)); // Vietnam.png -> Vietnam
            }
            return lstNames; 
        }

        //Function get random name of country from list without duplicate

       private static List<string> getNameRandom(string name, List<string> lstNames)
        {
            HashSet<string> myHashSet = new HashSet<string>();
            myHashSet.Add(name);  // first we need add name
            // second, we will random some other name with no duplication with name 
            while(myHashSet.Count <4) // We need 4 names for 4 answer
            {
                //Add random name to HashSet
                myHashSet.Add(lstNames.OrderBy(s => Guid.NewGuid()).First());
            }

            return myHashSet.OrderBy(s => Guid.NewGuid()).ToList(); // Random all answer
            
        }

        //Function to generate query INSERT INTO for create Database Question
        private static async Task generateQuery()
        {
            List<string> lstQuery = new List<string>();
            List<string> lstCountryName = getListCountry();
            string query = String.Empty; 

            foreach(var name in lstCountryName)
            {
                //With one value in lstCountryName, we create one
                //question with 4 answer and 1 correct answer
                List<string> answerList = getNameRandom(name, lstCountryName);
                query = "INSERT INTO Question(Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer)"
                    + $"VALUES(\"{name}\", \"{answerList[0]}\", \"{answerList[1]}\", \"{answerList[2]}\", \"{answerList[3]}\", \"{name}\");";
                lstQuery.Add(query); // add query we just create to list
            }
            //Write all to file
            System.IO.File.WriteAllLines(@".//QueryGenerate.txt", lstQuery);

        }
        static void Main(string[] args)
        {
            Console.WriteLine("TOOL GENERATE QUERY 1.0");
            Console.WriteLine("Please wait...");
            generateQuery();
            Console.WriteLine("Success...");
            Console.ReadKey();
        }
    }
}
