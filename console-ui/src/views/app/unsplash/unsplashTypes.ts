export interface Photo {
  id: string;
  created_at: Date;
  updated_at: Date;
  promoted_at?: Date;
  width: number;
  height: number;
  color: string;
  blur_hash: string;
  description: string;
  alt_description: string;
  urls: any;
  links: any;
  likes: number;
  liked_by_user: boolean;
  current_user_collections: any[];
  sponsorship?: any;
  topic_submissions: any;
  user: User;
  tags_preview: any[];
}

export interface Collection {
  id: string;
  title: string;
  description: string;
  published_at: Date;
  last_collected_at: Date;
  updated_at: Date;
  curated: boolean;
  featured: boolean;
  total_photos: number[];
  private: boolean;
  share_key: string;
  tags: any[];
  links: any;
  user: User;
  cover_photo: any;
  preview_photos: any[];
}

export interface User {
  id: string;
  updated_at: Date;
  username: string;
  name: string;
  first_name: string;
  last_name: string;
  twitter_username: string;
  portfolio_url: string;
  bio: string;
  location: string;
  links: any;
  profile_image: any;
  instagram_username: string;
  total_collections: number;
  total_likes: number;
  total_photos: number;
  accepted_tos: boolean;
  for_hire: boolean;
  social: any;
  followed_by_user: boolean;
  photos: any[];
  badge: any;
  tags: any;
  followers_count: number;
  following_count: number;
  allow_messages: boolean;
  numeric_id: number;
  downloads: number;
  meta: any;
}
