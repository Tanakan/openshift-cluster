import React from 'react';
import { ComponentStory, ComponentMeta, storiesOf } from '@storybook/react';

import MenuPage from '../pages/MenuPage';
import { MemoryRouter } from 'react-router-dom';
import Header from '../pages/common/Header';
import { Menu } from '../models/Menu';

storiesOf('メニュー表', module)
.addDecorator(story => (
  <MemoryRouter initialEntries={['/']}>{story()}</MemoryRouter>
))
  .add('nomal', () => <>
    <Header userProfile={{
      employeeId: 'test_id',
      name: 'testさん',
      mailAddress: 'test@example.com'
    }}/>
    <MenuPage menues={menues}/>
  </>);

const menues:Menu[] = [
    {
      "id": 1,
      "name": "牛丼",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 829,
        "protein": 78.4,
        "fat": 15.5,
        "carbo": 3.3,
        "salt": 9
      }
    },
    {
      "id": 2,
      "name": "きつねそば",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 394,
        "protein": 49,
        "fat": 1.2,
        "carbo": 10.3,
        "salt": 4
      }
    },
    {
      "id": 3,
      "name": "豚の生姜焼き定食",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 983,
        "protein": 78,
        "fat": 15.5,
        "carbo": 3.3,
        "salt": 9
      }
    },
    {
      "id": 4,
      "name": "秋の味覚スペシャルランチ",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 789,
        "protein": 98.2,
        "fat": 19.2,
        "carbo": 23,
        "salt": 12
      }
    },
    {
      "id": 5,
      "name": "特製エビチャーハン",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 829,
        "protein": 98.3,
        "fat": 20.2,
        "carbo": 11.4,
        "salt": 12
      }
    },
    {
      "id": 6,
      "name": "月見うどん",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 387,
        "protein": 68.7,
        "fat": 6.4,
        "carbo": 17.5,
        "salt": 4.9
      }
    },
    {
      "id": 7,
      "name": "焼きカレー",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 631,
        "protein": 69,
        "fat": 14.4,
        "carbo": 13.2,
        "salt": 8
      }
    },
    {
      "id": 8,
      "name": "ナスと豚のオイスター炒め",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 739,
        "protein": 55,
        "fat": 13.2,
        "carbo": 16,
        "salt": 10
      }
    },
    {
      "id": 9,
      "name": "鳥の唐揚げ定食",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 580,
        "protein": 58,
        "fat": 13.2,
        "carbo": 12.5,
        "salt": 7.5
      }
    },
    {
      "id": 10,
      "name": "塩サバ焼き定食",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 300,
        "protein": 51,
        "fat": 11.4,
        "carbo": 20,
        "salt": 8.1
      }
    },
    {
      "id": 11,
      "name": "牛サガリの味噌焼き",
      "price": 300,
      "createdAt": new Date(),
      "tuition": {
        "cal": 650,
        "protein": 22,
        "fat": 5.4,
        "carbo": 15.5,
        "salt": 7
      }
    }
  ];