using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Models;

namespace Helpers.ScheduleHandler.Interfaces
{
    public interface IGetScheduleRepository
    {
        public TimeTable GetTimeTableByGroupId(int id);
    }
}