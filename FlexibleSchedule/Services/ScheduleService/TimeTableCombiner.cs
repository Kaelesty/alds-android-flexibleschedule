using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using FlexibleSchedule.Services.DataBaseService;
using Helpers.ScheduleHandler.Interfaces;
using Models;

namespace Helpers.ScheduleHandler
{
    public class TimeTableCombiner : ITimeTableCombiner 
    {
        public TimeTable GetFullSchedule(List<TimeTable> timeTables, Dictionary<int,int> priorities)
        {
            Console.WriteLine("1");
            TimeTable resTimeTable = new TimeTable()
            {
                Days = new List<Day>()
                {
                    new Day()
                    {
                        Pairs = new List<Pair>()
                    },
                    new Day()
                    {
                        Pairs = new List<Pair>()
                    },
                    new Day()
                    {
                        Pairs = new List<Pair>()
                    },
                    new Day()
                    {
                        Pairs = new List<Pair>()
                    },
                    new Day()
                    {
                        Pairs = new List<Pair>()
                    },
                    new Day()
                    {
                        Pairs = new List<Pair>()
                    },
                    new Day()
                    {
                        Pairs = new List<Pair>()
                    },
                    
                }
            };
            Dictionary<int,int> sortedPriorities = priorities
                .OrderBy(x => x.Value)
                .ToDictionary(x => x.Key, x => x.Value);
            foreach (var priority in sortedPriorities)
            {

                List<Day> days = timeTables.FirstOrDefault(t => t.id == priority.Key).Days;
                for(int i =0;i<days.Count;i++)
                {
                    foreach (var pair in days[i].Pairs)
                    {
                        if (!isDayContainPairWithTime(resTimeTable.Days[i], pair.Time))
                        {
                            resTimeTable.Days[i].Pairs.Add(pair);
                            
                        }

                    }
                }
            }

            return resTimeTable;
        }

        private bool isDayContainPairWithTime(Day day,string time)
        {
            foreach (var Pair in day.Pairs)
            {
                if (Pair.Time == time)
                {
                    return true;
                }
            }

            return false;
        }
        
        
    }
}