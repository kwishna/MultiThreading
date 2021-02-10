using System;
namespace CodingExercises.Refactoring
{
	public enum PlayerClass
	{
		Warrior,
		Mage,
		Archer
	}

	public class Armor
	{
		public bool IsLight { get; set; }
	}

	public class Player
	{
		public Armor Armor { get; set; }

		public int Attack(PlayerClass playerClass)
		{
			switch(playerClass)
			{
				case PlayerClass.Warrior:
					return SwingSword();
				case PlayerClass.Mage:
					return CastSpell();
				case PlayerClass.Archer:
					return ShootArrow();
				default:
					return 0;
			}
		}

		public bool CanWearArrmor(PlayerClass playerClass)
		{
			switch (playerClass)
			{
				case PlayerClass.Warrior:
				return false;

				case PlayerClass.Mage:
				return Armor.IsLight;

				case PlayerClass.Archer:
				return false;

				default:
				return false;
			}
		}

		private int SwingSword()
		{
			var x = new Random(1000);
			return x.Next()%10;
		}

		int CastSpell()
		{
			return DateTime.Now.Millisecond % 20;
		}

		private int ShootArrow()
		{
			return DateTime.Now.Millisecond % 10;
		}
	}
}