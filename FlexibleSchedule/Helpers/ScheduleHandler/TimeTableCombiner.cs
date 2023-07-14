using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using FlexibleSchedule.Services.DataBaseService;
using Helpers.ScheduleHandler.Interfaces;
using Models;

namespace Helpers.ScheduleHandler
{
    public class TimeTableCombiner 
    {
        public IEnumerable<IEnumerable<IEnumerable<string>>> GetSchedule (List<IEnumerable<IEnumerable<IEnumerable<string>>>> groupsSchedule){
            List<List<IEnumerable<string>>> returnedSchedule = new List<List<IEnumerable<string>>>();
            for(int i=0; i<7; i++){
                returnedSchedule.Add(new List<IEnumerable<string>>());
                foreach(IEnumerable<IEnumerable<IEnumerable<string>>> groupSchedule in groupsSchedule){
                    returnedSchedule[i].AddRange(groupSchedule.ToArray()[i]);
                }

                try
                {

                    returnedSchedule[i].Sort((x, y) =>
                    {
                        return Convert.ToInt32(x.ToArray()[0]) - Convert.ToInt32(y.ToArray()[0]);
                    });
                }
                catch
                {
                    continue;
                }

                    
            }
            
            return returnedSchedule;
            
        }
    }
}