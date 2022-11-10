import React from 'react';
import { ComponentStory, ComponentMeta, storiesOf } from '@storybook/react';

import Header from '../pages/common/Header';
import { MemoryRouter } from 'react-router-dom';

const userProfile = {
  name: 'Test',
  mailAddress: 'test@exmaple.com',
  employeeId:'11111'
}

storiesOf('Header', module)
  .addDecorator(story => (
      <MemoryRouter initialEntries={['/']}>{story()}</MemoryRouter>
  ))
  .add('normal', () => <Header userProfile={userProfile}/>)