
public class Healthworker {
	private int age;
	private String name;
	public String gender; 
	public Healthworker(int age, String name, String gender)
	{
		this.setInfo(age, name, gender);
		}
	private void setInfo(int age, String name, String gender)
	{
		this.age = age;
		this.name = name;
		this.gender = gender;
		}
	public String Advice(String ad)
	{
		return "My advice for" +ad+"is...";
		}
public String Emergency()
{
	return "There is a emergency!" ;
	}
}