require 'rails_helper'
require 'capybara/rails'


RSpec.describe "Searches", :type => :feature do
  describe "GET /searches/index" do
    it "should show searches from diffrent search providers" do
      visit "/searches/index"
      expect(page).to	have_content "Find me in app"
    end
  end
end
