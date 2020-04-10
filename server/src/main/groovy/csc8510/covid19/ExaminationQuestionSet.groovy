package csc8510.covid19

class ExaminationQuestionSet
{

  private static ExaminationQuestionSet ourInstance = new ExaminationQuestionSet();
  def questionSet

  static ExaminationQuestionSet getInstance()
  {
    ourInstance.questionSet = getQuestions()
    return ourInstance
  }

  private static getQuestions(){
    def questions = new ArrayList<HealthProviderPrompt>()

    def questionText = "Have you traveled outside the country in the past 30 days?"
    def answers = new ArrayList<HealthProviderPromptAnswer>();
    answers.add(new HealthProviderPromptAnswer("Yes", 30))
    answers.add(new HealthProviderPromptAnswer("No", 20))

    questions.add(new HealthProviderPrompt(questionText, answers))

    return questions


  }

  private ExaminationQuestionSet()
  {
  }
}
