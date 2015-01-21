package com.Calculo;

import java.util.ArrayList;
import java.util.List;

public class CalcApprovalVoting
{

	private int m_Max;
	private List<String> m_Choices = new ArrayList<String>();

	public
	CalcApprovalVoting (int numberOfChoices)
	{
		m_Max = numberOfChoices + 66;

		for(int i = 65; i < 65 + numberOfChoices; i++)
			Populate("", i);
	}

	private void
	Populate (String orig, int end)
	{
		orig += (char)end;

		for(int i = end+1; i < m_Max; i++)
		{
			if(!m_Choices.contains(orig))
				m_Choices.add(orig);
			Populate(orig, i);
		}
	}

	public List<String>
	getPossible ()
	{
		return m_Choices;
	}


}