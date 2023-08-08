using Models;

namespace Helpers.ScheduleHandler.Interfaces;

public interface ITimeTableCombiner
{
    TimeTable GetFullSchedule(List<TimeTable> timeTables, Dictionary<int, int> priorities);
}