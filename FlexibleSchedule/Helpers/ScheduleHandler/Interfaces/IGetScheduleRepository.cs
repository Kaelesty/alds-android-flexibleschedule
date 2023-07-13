using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Helpers.ScheduleHandler.Interfaces
{
    public interface IGetScheduleRepository
    {
        //Функция которая из бд должна возвращать двумерный массив, в котором (дни X пары X инфа о парах)
        //В инфе о парах первая колонка должна быть сравниваемой, тоесть либо время пары, либо номер пары.
        public IEnumerable<IEnumerable<IEnumerable<string>>> GetTimeTableById(int id);
    }
}