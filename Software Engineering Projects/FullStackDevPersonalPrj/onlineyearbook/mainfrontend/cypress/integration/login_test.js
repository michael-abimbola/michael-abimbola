/// <reference types = "cypress"/>


it('login test', () => {
    cy.visit("http://localhost:3000/")

    cy.get('[type="text"]').type("3870707")

    cy.get('[type="password"]').type("Mikoko@02")

    cy.get('[type="submit"]').click()

    cy.get('[href="/graduationClass"]').click()

    cy.contains(`Add Graduation Class`)

})
