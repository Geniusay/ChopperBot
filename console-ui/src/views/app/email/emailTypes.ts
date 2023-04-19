export type Email = {
  id: number;
  read: boolean;
  starred: boolean;
  time: string;
  subject: string;
  title: string;
  content: string;
  labels: string[];
};
