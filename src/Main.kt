
package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 6) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            1 -> {
                print("Digite o nome do cachorro: ")
                val nomeCachorro = readLine()
                print("Digite a cor do cachorro: ")
                val corCachorro = readLine()?.let { Cor.valueOf(it.uppercase()) }
                val cachorro = Cachorro(0, corCachorro ?: Cor.OUTRA)
                cachorro.nome = nomeCachorro ?: ""
                repositorioAnimal.adicionar(cachorro)
            }
            2 -> {
                print("Digite o nome do gato: ")
                val nomeGato = readLine()
                print("Digite a cor do gato: ")
                val corGato = readLine()?.let { Cor.valueOf(it.uppercase()) }
                val gato = Gato(0, corGato ?: Cor.OUTRA)
                gato.nome = nomeGato ?: ""
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                print("Digite o nome do pássaro: ")
                val nomePassaro = readLine()
                print("Digite a cor do pássaro: ")
                val corPassaro = readLine()?.let { Cor.valueOf(it.uppercase()) }
                val passaro = Passaro(0, corPassaro ?: Cor.OUTRA)
                passaro.nome = nomePassaro ?: ""
                repositorioAnimal.adicionar(passaro)
            }

            4 -> {
                repositorioAnimal.listar()
            }
            5 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            7 -> {
                print("Digite o nome do animal a ser removido: ")
                val nomeRemover = readLine()
                val animalRemover = repositorioAnimal.animais.find { it.nome == nomeRemover }
                if (animalRemover != null) {
                    repositorioAnimal.remover(animalRemover)
                    println("$nomeRemover removido com sucesso.")
                } else {
                    println("Animal não encontrado.")
                }
            }
            8 -> {
                print("Digite a cor dos animais a serem listados: ")
                val corListar = readLine()
                val animaisPorCor = repositorioAnimal.listarPorCor(corListar)
                if (animaisPorCor.isNotEmpty()) {
                    animaisPorCor.forEach { println(it.nome) }
                } else {
                    println("Nenhum animal encontrado com a cor especificada.")
                }
            }
            9 -> {
                print("Digite a idade dos animais a serem listados: ")
                val idadeListar = readLine()?.toIntOrNull()
                if (idadeListar != null) {
                    val animaisPorIdade = repositorioAnimal.listarPorIdade(idadeListar)
                    if (animaisPorIdade.isNotEmpty()) {
                        animaisPorIdade.forEach { println(it.nome) }
                    } else {
                        println("Nenhum animal encontrado com a idade especificada.")
                    }
                } else {
                    println("Idade inválida.")
                }
            }
            10 -> {
                print("Digite o nome do animal a ser buscado: ")
                val nomeBuscar = readlnOrNull()
                val animalEncontrado = repositorioAnimal.buscarPorNome(nomeBuscar)
                if (animalEncontrado != null) {
                    println("Animal encontrado: ${animalEncontrado.nome}")
                } else {
                    println("Animal não encontrado - Null.")
                }
            }
            11 -> {
                print("Digite o nome da pessoa: ")
                val nomePessoa = readLine()
                val pessoa = Homem(0, Cor.BRANCO)
                pessoa.nome = nomePessoa ?: ""
                repositorioAnimal.adicionar(pessoa)
            }





        }

    }
}
enum class Cor {
    BRANCO,
    PRETO,
    MARROM,
    AMARELO,
    CINZA,
    OUTRA
}


abstract class Animal(var idade: Int,var cor: Cor) {
    open var nome: String = ""
        get() = "Animal: $field"
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()
    abstract fun idadeEmAnosHumanos(): Int

}

class Cachorro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
    override fun idadeEmAnosHumanos(): Int {
        return idade * 7
    }
}
class Gato(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
    override fun idadeEmAnosHumanos(): Int {
        return idade * 7
    }
}

class Passaro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
    override fun idadeEmAnosHumanos(): Int {
        return idade * 7
    }
}
class Homem(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun idadeEmAnosHumanos(): Int {
        return this.idade
    }

    override fun emitirSom() {
    }
}


fun menu() {

    println("1 - Cachorro")
    println("2 - Gato")
    println("3 - Pássaro")
    println("4 - Listar Animais")
    println("5 - Emitir som")
    println("6 - Sair")
    println("7 - Remover Animal")
    println("8 - Listar Animais por Cor")
    println("9 - Listar Animais por Idade")
    println("10 - Buscar Animal por Nome")
    println("11 - Criar Pessoa")



}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }
    fun remover(animal: Animal) {
        animais.remove(animal)
    }
    fun listarPorCor(cor: String?): List<Animal> {
        return animais.filter { it.cor.toString() == cor }
    }

    fun listarPorIdade(idade: Int): List<Animal> {
        return animais.filter { it.idade == idade }
    }
    fun buscarPorNome(nome: String?): Animal? {
        return animais.find { it.nome == nome }
    }


}