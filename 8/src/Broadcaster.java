
public class Broadcaster {
	private int age;
	private String name;
	public String gender; 
	public Broadcaster(int age, String name, String gender)
	{
		this.setInfo(age, name, gender);
		}
	private void setInfo(int age, String name, String gender)
	{
		this.age = age;
		this.name = name;
		this.gender = gender;
		}
	public String BroadcastInfo(String Info)
	{
		return "The" +Info+"will be Broadcasted";
		}
	}
