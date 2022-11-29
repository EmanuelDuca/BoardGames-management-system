public class Event
{
  private String title;
  private String description;
  private String image;
  private DateTime startDate;
  private DateTime endDate;


  public Event(String title, String description, String image, DateTime startDate, DateTime endDate)
  {
    this.title = title;
    this.description = description;
    this.image = image;
    this.startDate = startDate.copy();
    this.endDate = endDate.copy();
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getImage()
  {
    return image;
  }

  public void setImage(String image)
  {
    this.image = image;
  }

  public DateTime getStartDate()
  {
    return startDate;
  }

  public void setStartDate(DateTime startDate)
  {
    this.startDate = startDate.copy();
  }

  public DateTime getEndDate()
  {
    return endDate;
  }

  public void setEndDate(DateTime endDate)
  {
    this.endDate = endDate.copy();
  }
}