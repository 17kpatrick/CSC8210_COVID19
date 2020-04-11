package csc8510.covid19

import com.google.gson.GsonBuilder
import org.apache.commons.io.FileUtils

import java.nio.charset.StandardCharsets

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
    String data = FileUtils.readFileToString(new File("src\\main\\groovy\\csc8510\\covid19\\questionData\\questions.json"), StandardCharsets.UTF_8)
    def gsonBuilder = new GsonBuilder()
    def gson = gsonBuilder.create()
    return gson.fromJson(data, HealthProviderPrompt[].class)
  }

  private ExaminationQuestionSet()
  {
  }
}
