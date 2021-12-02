/// <reference types = "cypress"/>


it("google test", ()=> {
    cy.visit("https://www.google.com/")

    cy.get('#L2AGLb > .jyfHyd').click()

    //addign a timeout before the operation is considered failed
    cy.get('.gLFyf', {timeout: 6000}).type('Automation step by step{enter}')

    //cy.contains is used to search for elements using their text
    cy.contains(`Video's`).click()
})