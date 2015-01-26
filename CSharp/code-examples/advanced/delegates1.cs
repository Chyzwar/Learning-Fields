// Lecture 6: Advanced C# Constructs: Delegates

// p384

using System;
public class MediaStorage {
  // declare a delegate, ie. the type of the method
  public delegate int PlayMedia();

  // this is a higher-order function, ie. it uses a delegate
  public void ReportResult(PlayMedia playerDelegate) {
    if (playerDelegate() == 0) {
      Console.WriteLine("Media played successfully");
    } else {
      Console.WriteLine("Error in playing media.");
    }
  }
}

// now 2 classes, each with a possible instance of the delegate

public class AudioPlayer {
   private int audioPlayerStatus;
   public int PlayAudioFile() {
     Console.WriteLine("Playing audio file");
     audioPlayerStatus = 0;
     return audioPlayerStatus;
   }
}

public class VideoPlayer {
   private int videoPlayerStatus;
   public int PlayVideoFile() {
     Console.WriteLine("Playing video file");
     videoPlayerStatus = 0;
     return videoPlayerStatus;
   }
}

public class Tester {
  // in Main we use the higher-order function
  public static void Main () {
     // instantiate the storage class
     MediaStorage ms = new MediaStorage();
     // instantiate the player classes
     AudioPlayer aPlayer = new AudioPlayer();
     VideoPlayer vPlayer = new VideoPlayer();
     // instantiate the delegate
     MediaStorage.PlayMedia aDelegate = new MediaStorage.PlayMedia(aPlayer.PlayAudioFile);
     MediaStorage.PlayMedia vDelegate = new MediaStorage.PlayMedia(vPlayer.PlayVideoFile);
     // provide instances to the method using the delegate
     ms.ReportResult(aDelegate);
     ms.ReportResult(vDelegate);
  }
}
