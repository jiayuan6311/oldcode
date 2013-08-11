
public class Armedpolice 
	{
		private int age;
		private String name;
		public String gender; 
		public Armedpolice(int age, String name, String gender)
		{
			this.setInfo(age, name, gender);
		}
	 
		private void setInfo(int age, String name, String gender)
		{
			this.age = age;
			this.name = name;
			this.gender = gender;
	    }

		public String askforhelp(String problem)
		{
			return "I will help you through" +problem;
		}
	}
