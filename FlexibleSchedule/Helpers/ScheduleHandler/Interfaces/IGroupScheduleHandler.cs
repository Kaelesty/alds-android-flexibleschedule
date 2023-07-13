using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Models;

namespace Helpers.ScheduleHandler.Interfaces
{
    public interface IGroupScheduleHandler
    {
        public int id{ get; set; }
        public TimeTable TimeTable{ get; set; }

    }
}