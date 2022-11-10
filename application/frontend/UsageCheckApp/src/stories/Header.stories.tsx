import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';

import Header from '../pages/components/Header';

export default {
  title: 'Odango/Header',
  component: Header,
  parameters: {
    layout: 'fullscreen',
  },
} as ComponentMeta<typeof Header>;

const Template: ComponentStory<typeof Header> = (args) => <Header {...args} />;

export const LoggedIn = Template.bind({});
LoggedIn.args = {
  user:{
    name: 'Jane Doe',
    mailAddress: 'test@exmaple.com',
    employeeId:'11111'
  }
};

export const LoggedOut = Template.bind({});
LoggedOut.args = {};
