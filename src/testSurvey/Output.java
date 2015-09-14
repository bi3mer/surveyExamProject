package testSurvey;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class Output 
{
	private Voice voice;
	
	public Output() 
	{
		String voiceName = "kevin16";
        
        VoiceManager voiceManager = VoiceManager.getInstance();
        this.voice = voiceManager.getVoice(voiceName);
	}

	public void println(String message)
	{
		System.out.println(message);
		this.voice.allocate();
		this.voice.speak(message);
		//this.voice.deallocate();
	}
	
	public void print(String message)
	{
		System.out.print(message);
		this.voice.allocate();
		this.voice.speak(message);
		//this.voice.deallocate();
	}
	
}
