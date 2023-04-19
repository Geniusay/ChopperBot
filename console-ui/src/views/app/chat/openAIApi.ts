import { Configuration, OpenAIApi } from "openai";

export const createCompletion: any = (keyword: string, apiKey: string) => {
  const configuration = new Configuration({
    apiKey: apiKey,
  });

  const openai = new OpenAIApi(configuration);

  return openai.createChatCompletion({
    model: "gpt-3.5-turbo",
    messages: [{ role: "user", content: keyword }],
  });
};

export const translationApi: any = (
  content: string,
  prompt: string,
  apiKey: string
) => {
  const configuration = new Configuration({
    apiKey: apiKey,
  });

  const openai = new OpenAIApi(configuration);

  return openai.createChatCompletion({
    model: "gpt-3.5-turbo",
    messages: [
      { role: "user", content: prompt },
      { role: "user", content: content },
    ],
  });
};

// openai.listModels
// https://platform.openai.com/docs/api-reference/models/list

// openai.retrieveModel
// GET https://api.openai.com/v1/models/{model}

function generatePrompt(animal: string) {
  const capitalizedAnimal =
    animal[0].toUpperCase() + animal.slice(1).toLowerCase();
  return `Suggest three names for an animal that is a superhero.
  
  Animal: Cat
  Names: Captain Sharpclaw, Agent Fluffball, The Incredible Feline
  Animal: Dog
  Names: Ruff the Protector, Wonder Canine, Sir Barks-a-Lot
  Animal: ${capitalizedAnimal}
  Names:`;
}
