using Helpers.ScheduleHandler;
using Helpers.ScheduleHandler.Interfaces;
using Microsoft.EntityFrameworkCore;
using Models;
using Services.DataBaseService;

namespace FlexibleScheduleTests;

public class GroupRepositoryTests
{
    [Fact]
    public void TimeTableCombinerTest()
    {
        //arrange

        TimeTable timetable1 = new TimeTable()
        {
            id = 1,
            Days = new List<Day>()
            {
                new Day()
                {
                    Pairs = new List<Pair>()
                    {
                        new Pair()
                        {
                            Info = "Matan",
                            Time = "2",
                            Teacher = "Teacher",
                            Place = "place1"
                        },
                        new Pair()
                        {
                            Info = "neMatan",
                            Time = "3",
                            Teacher = "Teacher",
                            Place = "place2"
                        }
                        
                    }
                },
                new Day()
                {
                    Pairs = new List<Pair>()
                    {
                        new Pair()
                        {
                            Info = "Matan",
                            Time = "2",
                            Teacher = "Teacher",
                            Place = "place1"
                        },
                        new Pair()
                        {
                            Info = "11Matan11",
                            Time = "7",
                            Teacher = "Teacher",
                            Place = "pla4321"
                        }
                        
                    }     
                },
            }
        };
        TimeTable timetable2 = new TimeTable()
        {
            id = 2,
            Days = new List<Day>()
            {
                new Day()
                {
                    Pairs = new List<Pair>()
                    {
                        new Pair()
                        {
                            Info = "infa",
                            Time = "3",
                            Teacher = "nea",
                            Place = "231"
                        },
                        new Pair()
                        {
                            Info = "info",
                            Time = "100",
                            Teacher = "saddsa",
                            Place = "zxccxz"
                        }
                        
                    }
                },
                new Day()
                {
                    Pairs = new List<Pair>()
                    {
                        new Pair()
                        {
                            Info = "1321",
                            Time = "5",
                            Teacher = "123",
                            Place = "asdcx"
                        },
                        new Pair()
                        {
                            Info = "inf",
                            Time = "2",
                            Teacher = "Teacher3",
                            Place = "place14"
                        }
                        
                    }     
                },
            }
        };
        TimeTable excepted = new TimeTable()
        {
            Days = new List<Day>()
            {
                new Day()
                {
                    Pairs = new List<Pair>()
                    {
                        new Pair()
                        {
                            Info = "infa",
                            Time = "3",
                            Teacher = "nea",
                            Place = "231"
                        },
                        new Pair()
                        {
                            Info = "info",
                            Time = "100",
                            Teacher = "saddsa",
                            Place = "zxccxz"
                        },
                        new Pair()
                        {
                        Info = "Matan",
                        Time = "2",
                        Teacher = "Teacher",
                        Place = "place1"
                    },
                        
                    }
                },
                new Day()
                {
                    Pairs = new List<Pair>()
                    {
                        new Pair()
                        {
                            Info = "1321",
                            Time = "5",
                            Teacher = "123",
                            Place = "asdcx"
                        },
                        new Pair()
                        {
                            Info = "inf",
                            Time = "2",
                            Teacher = "Teacher3",
                            Place = "place14"
                        },
                        new Pair()
                        {
                        Info = "11Matan11",
                        Time = "7",
                        Teacher = "Teacher",
                        Place = "pla4321"
                        }
                        
                    }     
                },
            }
        };
        List<TimeTable> timeTables = new List<TimeTable>()
        {
            timetable1, timetable2
        };
        Dictionary<int, int> priority = new Dictionary<int, int>()
        {
            [1] = 2,
            [2] = 1
        };
        //act
        ITimeTableCombiner TBCombiner = new TimeTableCombiner();
        TimeTable result = TBCombiner.GetFullSchedule(timeTables,priority);
        //assert
        Assert.True(isEqualSchedule(result,excepted));
    }

    private bool isEqualSchedule(TimeTable result, TimeTable exception)
    {
        for(int i = 0;i < result.Days.Count;i++)
        {
            for(int j = 0;j< result.Days[i].Pairs.Count;j++)
            {
                Console.WriteLine($"{result.Days[i].Pairs[j].Time} -- {exception.Days[i].Pairs[j].Time}");
                if (result.Days[i].Pairs[j].Time != exception.Days[i].Pairs[j].Time)
                {
                    return false;
                }

            }
        }

        return true;
    }
}