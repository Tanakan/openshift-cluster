import React from 'react';
import { ComponentStory, ComponentMeta, storiesOf } from '@storybook/react';

import { MemoryRouter } from 'react-router-dom';
import Header from '../pages/common/Header';
import LunchHistoryPage from '../pages/LunchHistoryPage';
import { LunchHistory } from '../models/LunchHistory';

storiesOf('利用状況参照', module)
  .addDecorator(story => (
    <MemoryRouter initialEntries={['/']}>{story()}</MemoryRouter>
  ))
  .add('normal', () => <>    
    <Header userProfile={{
        employeeId: 'test_id',
        name: 'testさん',
        mailAddress: 'test@example.com'
    }}/>
    <LunchHistoryPage lunchHistories={lunchHistories}/>
  </>);

const lunchHistories:LunchHistory[] = [
  {
    id: 1,
    employee:'test',
    createdAt:new Date(),
    menuId: 1,
    price: 300,
    menu: {
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
    }
  }
]