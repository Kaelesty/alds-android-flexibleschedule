using System.Diagnostics;
using auth.Helpers;
using Microsoft.AspNetCore.Mvc;
using WebApplication1.DataTransfersObjects;
using Microsoft.AspNetCore.Http;
using Models;
using auth.Helpers;
using FlexibleSchedule.Services.DataBaseService;
using Helpers.ScheduleHandler;
using Helpers.ScheduleHandler.Interfaces;
using Microsoft.EntityFrameworkCore;
using Services.DataBaseService;
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

    [HttpPost]
    public IActionResult DeleteGroup(GroupsUsersDto dto)
    {
        Console.WriteLine(dto.Code);
        Console.WriteLine(dto.GroupId);
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

    [HttpGet]
    public IActionResult GetAllGroupCodes()
    {
        try
        {
            int userId = AuthCheck();

            return Ok(_groupRepository.GetAllCodesByUserId(userId));
        }
        catch (Exception)
        {
            return Unauthorized();
        }
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
            Group group_ = _groupRepository.Create(group);

            return Created("sd",group);
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

    private int AuthCheck()
    {
        var jwt = Request.Cookies["jwt"];

        var token = _jwtService.Verify(jwt);

        int userId = int.Parse(token.Issuer);

        return userId;
    }

}