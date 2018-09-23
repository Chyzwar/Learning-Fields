package assignment_2;

import java.util.Arrays;

/**
 * This class is creating a bowling competitor object
 * @author Stian
 *
 */

public class Bowler extends Competitor
{
	private String competitorLevel;
	private String competitorCountry;
	
	/**
	 * This constructor creates a bowler object based on the following parameters
	 * @param competitorNumber the competitor number for the bowler
	 * @param competitorName store name of competitor
	 * @param listOfScores the list of scores for the bowler
	 * @param competitorLevel the skill level of the bowler
	 * @param competitorCountry the country where the bowler is from
	 */
	public Bowler(int id, Name competitorName, int[] listOfScores, String competitorLevel, String competitorCountry)
	{
		super(id,competitorName,listOfScores);
		this.competitorLevel = competitorLevel;
		this.competitorCountry = competitorCountry;
	}
	
	
	/**
	 * Returns the competitor number of the bowler
	 * @return competitor number
	 */
	public int getCompetitorNumber()
	{
		return CompetitorList.getUserInput();
	}
	
	
	/**
	 * Returns the full name of the bowler
	 * @return competitor name
	 */
	public String getCompetitorName()
	{
		return competitorName.getFullName();
	}
	
	
	/**
	 * Returns the list of scores for the bowler
	 * @return list of scores
	 */
	public int[] getListOfScores()
	{
		return listOfScores;
	}
	
	
	/**
	 * Returning the overall score of the bowler, minus the highest and the lowest scores
	 * @return the overall score
	 */
	public double getOverallScore()
	{
		double overallScore = 0;
		int lengthMinusTwoElements;
		
		//Sorting the list so that the scores will go from the lowest to the highest 
		Arrays.sort(listOfScores);
		
		/* 
		 * Getting the total score of the array, but skipping the score at the first and last index.
		 * As it is sorted in beforehand, it will then not count the lowest or the highest score.
		 */
		for(int i = 1; i < listOfScores.length-1; i++)
		{
			overallScore = overallScore + listOfScores[i];
		}
		
		/*
		 * To get the average score of the elements, you have to divide the total scores with
		 * the length of the list, but since I took away the lowest and the highest score,
		 * the length should then be the length of the list minus 2
		 */
		lengthMinusTwoElements = listOfScores.length-2;
		overallScore = overallScore/lengthMinusTwoElements;
		
		/*
		 * Reformatting the value so that it only gives one decimal
		 */
		//String overallScoreString = String.format("%.1f", overallScore);
		return overallScore;
	}
	
	
	/**
	 * Returns the skill level of the bowler
	 * @return skill level
	 */
	public String getCompetitorLevel()
	{
		return competitorLevel;
	}
	
	
	/**
	 * Returns the country where the bowler is from
	 * @return country
	 */
	public String getCompetitorCountry()
	{
		return competitorCountry;
	}
	

	/**
	 * Setting a new skill level for the bowler. Accepted values are: Novice, Regular or Veteran. The values are case insensitive
	 * @param competitorLevel new skill level
	 */
	public void setCompetitorLevel(String competitorLevel)
	{
		if(competitorLevel.equalsIgnoreCase("Novice") || competitorLevel.equalsIgnoreCase("Regular") || competitorLevel.equalsIgnoreCase("Veteran"))
		{
			this.competitorLevel = competitorLevel;
		}
		else
		{
			System.out.println("Error: The level of the competitor must be either: Novice, Regular or Veteran");
		}
	}
	
	
	/**
	 * Setting a new country for where the bowler is from
	 * @param competitorCountry country
	 */
	public void setCompetitorCountry(String competitorCountry)
	{
		this.competitorCountry = competitorCountry;
	}
	
	
	/**
	 * Returns the full details for the bowler
	 * @return full details
	 */
	public String getFullDetails()
	{
		int competitorNumber= getId();
		
		String fullDetails = "Full details for " + competitorNumber + ":\nCompetitor #" + competitorNumber + " is " + competitorName.getFullName() + ".\n" +
				competitorName.getFullName() + " is a " + competitorLevel.toLowerCase() + " from " + competitorCountry + ", and received these scores: " + Arrays.toString(getListOfScores()).replace("[", "").replace("]", "") + ".\n" +
				"This gives an overall score of " + String.format("%.1f", getOverallScore());
		return fullDetails;
	}
	
	
	/**
	 * Returns the short details for the bowler
	 * @return short details
	 */
	public String getShortDetails()
	{
		String shortDetails = "Short details for " + getId() + ":\nCN " + getId() + " (" + competitorName.getShortName() + ") has overall score " + String.format("%.1f", getOverallScore());
		return shortDetails;
	}
	
	
	/**
	 * Returns a list of details for the bowler
	 * @return list of details
	 */
	public String getDetailsList()
	{
		String detailsList = String.format("%-5s%-30s%-20s%-15s%-20s%s", getId(),competitorName.getFullName(),
				competitorCountry,competitorLevel,Arrays.toString(getListOfScores()).replace("[", "").replace("]", ""), String.format("%.1f", getOverallScore()));
		return detailsList;
	}

}
