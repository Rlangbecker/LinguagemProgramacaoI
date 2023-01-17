package br.com.fundatec.service;

import br.com.fundatec.Exception.RegraDeNegocioException;
import br.com.fundatec.dto.AgenciaCreateDTO;
import br.com.fundatec.dto.BancoCreateDTO;
import br.com.fundatec.dto.BancoDTO;
import br.com.fundatec.model.Banco;
import br.com.fundatec.repository.BancoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1> Classe BancoService</h1>
 *
 * <p> Classe responsável pelo CRUD e por gerir as regras de negocio.</p>
 * <p> Contem vinculo direto com BancoRepository para poder persistir as informações necessárias</p>
 * <p>Possui os seguintes métodos</p>
 * <ul>
 *    <li>verificarSeBancoExiste - <b>private</b></li>
 *    <li>create - <b>public</b></li>
 *    <li>update - <b>public</b></li>
 *    <li>bancoFindById - <b>public</b></li>
 *    <li>findById - <b>private</b></li>
 *    <li>list - <b>public</b></li>
 *    <li>delete - <b>public</b></li>
 * </ul>
 *
 * @author Cailan Grott e Ricardo Langbecker
 * @see BancoRepository
 * @since 1.0
 */

@Service
@RequiredArgsConstructor
public class BancoService {

    private final BancoRepository bancoRepository;
    private final ObjectMapper objectMapper;

    /**
     * Metodo para verificar se ha tentativa de duplicidade no Banco de dados
     *
     * @param bancoCreateDTO BancoCreateDTO
     * @return {@link Void} void
     * @throws RegraDeNegocioException - Caso o bancoCreateDTO possua informacoes iguais a algum outro banco que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     *                                 <p>Motivos para lancar a excecao: </p>
     *                                   <ul>
     *                                      <li>Banco com o mesmo nome ja existente no banco de dados</li>
     *                                      <li>Banco com o mesmo código ja existente no banco de dados</li>
     *                                      <li>Banco com o mesmo cnpj ja existente no banco de dados</li>
     *                                 </ul>
     */
    private void verificarSeBancoExiste(BancoCreateDTO bancoCreateDTO) throws RegraDeNegocioException {
        Optional<Banco> bancoFoundByNome = bancoRepository.findBancoByNome(bancoCreateDTO.getNome());
        if (bancoFoundByNome.isPresent()) {
            throw new RegraDeNegocioException("Banco com este nome já existe!");
        }

        Optional<Banco> bancoFoundByCnpj = bancoRepository.findBancoByCnpj(bancoCreateDTO.getCnpj());
        if (bancoFoundByCnpj.isPresent()) {
            throw new RegraDeNegocioException("Banco com este cnpj já existe!");
        }

        Optional<Banco> bancoFoundByCodigo = bancoRepository.findBancoByCodigo(bancoCreateDTO.getCodigo());
        if (bancoFoundByCodigo.isPresent()) {
            throw new RegraDeNegocioException("Banco com este codigo já existe!");
        }
    }

    /**
     * Metodo para criar uma nova entidade de Banco
     *
     * @param bancoCreateDTO BancoCreateDTO
     * @return {@link BancoCreateDTO} bancoCreateDTO
     * @throws RegraDeNegocioException - Caso o bancoCreateDTO possua informacoes iguais a algum outro banco que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see BancoCreateDTO
     * @see BancoDTO
     */
    public BancoDTO create(BancoCreateDTO bancoCreateDTO) throws RegraDeNegocioException {

        verificarSeBancoExiste(bancoCreateDTO);

        Banco bancoRetorno = objectMapper.convertValue(bancoCreateDTO, Banco.class);

        BancoDTO bancoDTO = objectMapper.convertValue(bancoRepository.save(bancoRetorno), BancoDTO.class);

        return bancoDTO;
    }

    /**
     * Metodo para editar banco ja existente no Banco de dados
     *
     * @param bancoCreateDTO bancoCreateDTO
     * @param idBanco        Integer
     * @return {@link BancoDTO} bancoDTO
     * @throws RegraDeNegocioException - Caso o bancoCreateDTO possua informacoes iguais a algum outro banco que exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see BancoCreateDTO
     */
    public BancoDTO update(Integer idBanco, BancoCreateDTO bancoCreateDTO) throws RegraDeNegocioException {
        Banco bancoRetorno = findById(idBanco);

        verificarSeBancoExiste(bancoCreateDTO);

        bancoRetorno.setNome(bancoCreateDTO.getNome());
        bancoRetorno.setCnpj(bancoCreateDTO.getCnpj());
        bancoRetorno.setCodigo(bancoCreateDTO.getCodigo());

        bancoRepository.save(bancoRetorno);

        BancoDTO bancoDTO = objectMapper.convertValue(bancoRetorno, BancoDTO.class);

        return bancoDTO;
    }

    /**
     * Metodo para buscar banco por ID existente no Banco de dados
     *
     * @param idBanco Integer
     * @return {@link BancoDTO} bancoDTO
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see BancoDTO
     */
    public BancoDTO bancoFindById(Integer idBanco) throws RegraDeNegocioException {
        Banco banco = findById(idBanco);

        BancoDTO bancoDTO = objectMapper.convertValue(banco, BancoDTO.class);
        return bancoDTO;
    }

    /**
     * Metodo para buscar banco por ID existente no Banco de dados
     *
     * @param idBanco Integer
     * @return {@link BancoDTO} bancoDTO
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     * @see Banco
     */
    private Banco findById(Integer idBanco) throws RegraDeNegocioException {
        Optional<Banco> bancoRetorno = bancoRepository.findById(idBanco);
        if (bancoRetorno.isEmpty()) {
            throw new RegraDeNegocioException("Banco com ID " + idBanco + " não encontrado!");
        }
        Banco banco = new Banco();
        banco.setIdBanco(bancoRetorno.get().getIdBanco());
        banco.setNome(bancoRetorno.get().getNome());
        banco.setCnpj(bancoRetorno.get().getCnpj());
        banco.setCodigo(bancoRetorno.get().getCodigo());

        return banco;
    }

    /**
     * Metodo para buscar uma lista com todos os bancos existentes no Banco de dados
     *
     * @return {@link BancoDTO} bancoDTO.
     */
    public List<BancoDTO> list() {
        return bancoRepository.findAll().stream()
                .map(banco -> objectMapper.convertValue(banco, BancoDTO.class))
                .collect(Collectors.toList());

    }

    /**
     * Metodo para deletar banco por ID existente no Banco de dados
     *
     * @param idBanco Integer
     * @throws RegraDeNegocioException - Caso o ID informado nao exista no banco de dados
     *                                 ele lanca uma excecao, informando o motivo.
     */
    public void delete(Integer idBanco) throws RegraDeNegocioException {
        Banco banco = findById(idBanco);

        bancoRepository.deleteById(banco.getIdBanco());
    }

}
