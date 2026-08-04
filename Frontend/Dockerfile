# Build stage
FROM node:20-alpine AS build
WORKDIR /app

# Install dependencies first (only lockfile changes invalidate this layer)
COPY package.json package-lock.json ./
RUN npm ci

COPY . .

# Base API URL baked into the bundle. Override at build time for deployed
# environments, e.g. docker build --build-arg VITE_API_URL=https://api.example.com/api
ARG VITE_API_URL=http://localhost:8080/api
ENV VITE_API_URL=$VITE_API_URL

RUN npm run build

# Runtime stage
FROM nginx:1.27-alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]