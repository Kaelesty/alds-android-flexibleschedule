using auth.Helpers;
using FlexibleSchedule.Services.DataBaseService;
using Services.DataBaseService;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllersWithViews();
builder.Services.AddScoped<IUserRepository, UserRepository>();
builder.Services.AddScoped<IGroupRepository,GroupRepository>();
builder.Services.AddScoped<JwtService>();
builder.Services.AddDbContext<Context>(options =>{
    string configurationName;
    switch (System.Environment.MachineName)
    {
        case "DESKTOP-AI7DA69":
            configurationName = "alexbelks";
            break;
        case "LAPTOP-NCL2H5HG":
            configurationName = "Evgesha";
            break;
        case "LAPTOP-BKG9A85Q":
            configurationName = "kaelesty";
            break;
        default:
            configurationName = "Jordenn";
            break;
    }
    options.UseMySql(builder.Configuration.GetConnectionString(configurationName),new MySqlServerVersion(new Version(10, 1, 40)));
});
var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthorization();
app.MapControllers();
app.MapControllerRoute(
    name: "default",
    pattern: "{controller}/{action=Index}/{id?}");

app.MapFallbackToFile("index.html");

app.Run();