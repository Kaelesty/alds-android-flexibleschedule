using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;


namespace Helpers.ScheduleHandler.Interfaces
{
    public interface IUserScheduleHandler< T> where T: IGroupScheduleHandler
    {
        public List<T> Groups {get; set;}

    }
}