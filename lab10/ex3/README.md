# Execício 3
## 1) Problema
O aeroporto Francisco Sá Carneiro está a ter um problema bastante sério: o cockpit de cada transporte aéreo contacta todos os outros para saber se existe algum na sua rota e é uma grande confusão... os pilotos quase que entram em desespero! Assim, o aeroporto precisa de uma torre de controlo que estabeleça a comunicação entre todos os vôos, facilitando o processo de comunicação. Um vôo é caraterizado por um tipo (Avião, Helicóptero ou Zepelim), um modelo e realiza uma rota específica. A função da torre de controlo é comunicar a um novo vôo se existe(m), ou não, vôo(s) que partilham a mesma rota, de forma a que o piloto desse vôo saiba se deve, ou não, prosseguir com uma determinada ação e evitando, assim, possíveis desastres aéreos.

## 2) Solução
A solução passa por implementar o padrão Mediator para controlar as comunicações. É então criada uma interface para o mediator, que neste caso é definido pela classe Torre. Todos os veículos são então controlados pelo mediator. A função deste é informar o veiculo que acabou de descolar de todos os outros veículos que estão na mesma rota que o próprio. A classe Torre é a responsável também por adicionar novos vôos e, caso já exista algum com essa rota, este deve ser avisado do mesmo. Caso contrário, esse Veiculo pode prosseguir uma vez que tem o caminho livre.

## 3) Referências para recursos/fontes utilizados
- Slides teóricos dos "Behavior Patterns"
- [Refactoring Guru](https://refactoring.guru/design-patterns/mediator)

# Autores
- João Reis, 98474
- Ricardo Rodriguez, 98388