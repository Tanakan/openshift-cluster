import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';

import PaymentHistoryPage from '../pages/LaunchHistoryPage';


export default {
  title: 'Odango/Payment',
  component: PaymentHistoryPage,
  parameters: {
    layout: 'fullscreen',
  },
} as ComponentMeta<typeof PaymentHistoryPage>;

const Template: ComponentStory<typeof PaymentHistoryPage> = (args) => <PaymentHistoryPage {...args} />;

export const Payment = Template.bind({});
Payment.args = {
  launchHistroies:[]
};