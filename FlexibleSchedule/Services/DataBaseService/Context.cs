using Models;
using Microsoft.EntityFrameworkCore;

namespace Services.DataBaseService;

public class Context : DbContext
{

    public Context(DbContextOptions<Context> options): base(options)
    {
        Database.EnsureCreated();

    }

    public DbSet<User> Users { get; set; }
    public DbSet<TimeTable> TimeTables { get; set; }
    public DbSet<Group> Groups { get; set; }


    

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);
        modelBuilder.Entity<User>(entity => entity.HasIndex(e => e.email).IsUnique());
        
        modelBuilder.Entity<Group>(entity =>
            {
                entity
                    .HasMany(u => u.Users)
                    .WithMany(g => g.Groups)
                    .UsingEntity<GroupsUsers>(
                    group => group
                        .HasOne(u => u.Users)
                        .WithMany(a => a.GroupsUsers)
                        .HasForeignKey(pa => pa.UserId),
                    publisher => publisher
                        .HasOne(pa => pa.Groups)
                        .WithMany(p => p.GroupsUsers)
                        .HasForeignKey(pa => pa.GroupId)
                );
            }
        );


        
    }
  

}
