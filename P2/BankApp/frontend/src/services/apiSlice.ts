// src/features/api/authApi.ts
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import { RootState } from './store';

interface User {
  email: string;
  firstName: string;
  lastName: string;
  balance: number;
}

interface AuthResponse {
  jwtToken: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export const apiSlice = createApi({
  reducerPath: 'authApi',

  baseQuery: fetchBaseQuery({
    baseUrl: 'http://localhost:8080/api',
    prepareHeaders: (headers, { getState }) => {
      const token = (getState() as RootState).auth.token;
      console.log('TOKEN', token);
      if (token && token != 'undefined') {
        console.log('Auth header set!');
        headers.set('Authorization', `Bearer ${token}`);
      }
      return headers;
    },
  }),

  endpoints: (builder) => ({
    register: builder.mutation<AuthResponse, RegisterRequest>({
      query: (body) => ({
        url: '/auth/register',
        method: 'POST',
        body,
      }),
    }),
    authenticate: builder.mutation<AuthResponse, LoginRequest>({
      query: (body) => ({
        url: '/auth/authenticate',
        method: 'POST',
        body,
      }),
    }),
    getUserInfo: builder.query<User, void>({
      query: () => ({
        url: '/get-user-info',
        method: 'GET',
      }),
    }),
  }),
});

export const {
  useRegisterMutation,
  useAuthenticateMutation,
  useGetUserInfoQuery,
} = apiSlice;
