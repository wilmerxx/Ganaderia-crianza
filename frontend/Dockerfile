FROM node:17-alpine as base

WORKDIR /app

COPY ../package.json /app

RUN npm install

FROM base as dev

ONBUILD COPY .. /app

ONBUILD RUN npm run build --prod

EXPOSE 4200

CMD ["npm", "run", "start"]
