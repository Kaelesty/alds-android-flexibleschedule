using auth.Helpers;
using Microsoft.AspNetCore.Mvc;
using WebApplication1.DataTransfersObjects;
using Models;
using FlexibleSchedule.Services.DataBaseService;
using Helpers.ScheduleHandler;
namespace Controllers;
[ApiController]
[Route("api/[controller]/[action]")]

public class GroupController : ControllerBase
{
    private readonly JwtService _jwtService;
    private readonly IGroupRepository _groupRepository;
    
    public GroupController(IGroupRepository groupRepository,JwtService jwtService)
    {
        _groupRepository = groupRepository;
        _jwtService = jwtService;
    }
    
    [HttpPost]
    public IActionResult CreateGroup(GroupDto groupDto)
    {
        try
        {
            int creatorId = AuthCheck();
            
            Group group = new Group
            {
                Code = groupDto.Code,

                TimeTable = groupDto.TimeTable,
                CreatorId = creatorId,
                Users = new List<User>()

            };
            bool isCreated = _groupRepository.Create(group);
            if (isCreated)
            {
                return Ok();
            }
            return BadRequest("NotCreated");

        }
        catch (Exception)
        {
            return Unauthorized();
        }

    }
    
    [HttpPost]
    public IActionResult DeleteGroup(GroupsUsersDto dto)
    {
        try
        {
            int userId = AuthCheck();
            
            _groupRepository.DeleteGroup(dto,userId);

            return Ok();
        }
        catch (Exception)
        {
            return Unauthorized();
        }
    }
    
    [HttpPost]
    public IActionResult ConnectToGroup(ConnectGroupDto CgroupDto)
    {
        try
        {
            int userId = AuthCheck();

            _groupRepository.ConnectToGroup(CgroupDto, userId);

            return (Ok());
        }
        catch (Exception)
        {
            return Unauthorized();
        }
        
    }

    [HttpPost]
    public IActionResult ChangePriority(GroupsUsersDto dto )
    {
        int userId = AuthCheck();
        _groupRepository.ChangePriority(userId,dto.GroupId,dto.priority);
        return Ok();
    }
    
    
    
    [HttpGet]
    public IActionResult GetFullTimeTable()
    {
        try
        {
            TimeTableCombiner timeTableCombiner = new TimeTableCombiner();
            int userId = AuthCheck();
            List<TimeTable> timeTables = _groupRepository.GetAllTimeTables(userId);
            Dictionary<int,int> priorities = _groupRepository.GetAllPriorities(userId);
            return Ok(timeTableCombiner.GetFullSchedule(timeTables,priorities));
        }
        catch (Exception)
        {
            return Unauthorized();
        }
    }



    [HttpGet]
    public IActionResult GetAllGroupCodes()
    {
        try
        {
            int userId = AuthCheck();
            List<GroupsUsersDto> Codes = _groupRepository.GetAllCodesByUserId(userId);
            Console.WriteLine();
            return Ok(Codes);
        }
        catch (Exception)
        {
            return Unauthorized();
        }
    }
    


    private int AuthCheck()
    {
        var jwt = Request.Cookies["jwt"];

        var token = _jwtService.Verify(jwt);

        int userId = int.Parse(token.Issuer);

        return userId;
    }

}