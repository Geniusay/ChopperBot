import axios from "axios";

export const textToSpeech = async () => {
  const googleInstance = axios.create({
    baseURL: "https://us-central1-texttospeech.googleapis.com",
    timeout: 100000,
  });

  const res = await googleInstance.post(
    "/v1/chat/completions/text:synthesize",
    {
      audioConfig: {
        audioEncoding: "LINEAR16",
        effectsProfileId: ["small-bluetooth-speaker-class-device"],
        pitch: 0,
        speakingRate: 1,
      },
      input: {
        text: "Google Cloud Text-to-Speech enables developers to synthesize natural-sounding speech with 100+ voices, available in multiple languages and variants. It applies DeepMind’s groundbreaking research in WaveNet and Google’s powerful neural networks to deliver the highest fidelity possible. As an easy-to-use API, you can create lifelike interactions with your users, across many applications and devices.",
      },
      voice: {
        languageCode: "en-US",
        name: "en-US-Neural2-J",
      },
    },
    {
      headers: {
        "Content-Type": "application/json",
        "x-goog-api-key": "AIzaSyBSXdkeyAvIZX5n_bj4KsqSjJf1W-_TfCntvk",
      },
    }
  );
};
