export interface DecodedJwtData {
    userId: number;
    sub: string;
    role: string;
    exp: number;
}